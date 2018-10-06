package com.trungnguyen.android.houston123.util;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.security.auth.x500.X500Principal;

import timber.log.Timber;

/**
 * Created by trungnd4 on 06/10/2018.
 */

@Singleton
public class Security {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String CIPHER_TYPE_MODE = "RSA/ECB/PKCS1Padding";
    private static final String CIPHER_PROVIDER = "AndroidOpenSSL";
    private static final String CIPHER_PROVIDER_M = "AndroidKeyStoreBCWorkaround";
    private static final String KEY_ALGORITHM = "RSA";
    private KeyStore mKeyStore;
    @NonNull
    private String mAlias = "KEY";
    private Context mContext;

    @Inject
    public Security(Context mContext) {
        this.mContext = mContext;
    }

    private static Cipher getCipher() throws Exception {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                // Error in android 6: InvalidKeyException: Need RSA private or public key
                return Cipher.getInstance(CIPHER_TYPE_MODE, CIPHER_PROVIDER);
            } else {
                // Error in lower android 6: NoSuchProviderException: Provider not available: AndroidKeyStoreBCWorkaround
                return Cipher.getInstance(CIPHER_TYPE_MODE, CIPHER_PROVIDER_M);
            }
        } catch (Exception exception) {
            throw new Exception("getCipher: Failed to get an instance of Cipher", exception);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void generateEncryptKey() throws Exception {
        if (mKeyStore != null) {
            return;
        }
        mKeyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        mKeyStore.load(null);
        if (!mKeyStore.containsAlias(mAlias)) {
            Calendar startDay = Calendar.getInstance();
            Calendar endDay = Calendar.getInstance();
            endDay.add(Calendar.YEAR, 1);
            KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(mContext)
                    .setAlias(mAlias)
                    .setSubject(new X500Principal("CN=" + mAlias))
                    .setSerialNumber(BigInteger.TEN)
                    .setStartDate(startDay.getTime())
                    .setEndDate(endDay.getTime())
                    .build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM, ANDROID_KEY_STORE);
            keyPairGenerator.initialize(spec);
            keyPairGenerator.generateKeyPair();
        }
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private byte[] encrytpData(@NonNull byte[] data) throws Exception {
        try {
            generateEncryptKey();
        } catch (Exception e) {
            throw e;
        }

        ByteArrayOutputStream outputStream = null;
        CipherOutputStream cipherOutputStream = null;
        byte[] result = null;
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(mAlias, null);
            PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();

            Cipher inCipher = getCipher();
            inCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            outputStream = new ByteArrayOutputStream();
            cipherOutputStream = new CipherOutputStream(outputStream, inCipher);
            cipherOutputStream.write(data);
        } catch (Exception e) {
            Timber.d(e, "Failed to encrypt data with %s", mAlias);
            throw e;
        } finally {
            if (cipherOutputStream != null) {
                try {
                    cipherOutputStream.close();
                    result = outputStream.toByteArray();
                    outputStream.close();
                } catch (IOException e) {
                    Timber.d(e, "IOException close file.");
                }
            }
        }
        return result;
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private byte[] decryptData(byte[] enryptData) throws Exception {
        try {
            generateEncryptKey();
        } catch (Exception e) {
            throw e;
        }

        CipherInputStream cipherInputStream = null;
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] result = null;
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(mAlias, null);
            PrivateKey privateKey = privateKeyEntry.getPrivateKey();

            Cipher outCipher = getCipher();
            outCipher.init(Cipher.DECRYPT_MODE, privateKey);

            inputStream = new ByteArrayInputStream(enryptData);
            cipherInputStream = new CipherInputStream(inputStream, outCipher);

            outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[512];
            int read;
            while ((read = cipherInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Timber.d(e, "Failed to decrypt data with %s", mAlias);
            throw e;
        } finally {
            if (inputStream != null && cipherInputStream != null && outputStream != null) {
                try {
                    inputStream.close();
                    result = outputStream.toByteArray();
                    outputStream.close();
                    cipherInputStream.close();
                } catch (IOException e) {
                    Timber.d(e, "IOException close file");
                }
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public String encrypt(@NonNull String plainText) throws Exception {
        if (TextUtils.isEmpty(plainText)) {
            throw new InvalidObjectException("Can not encrypt empty value");
        }
        byte[] textBytes;
        try {
            textBytes = plainText.getBytes("UTF-8");
        } catch (Exception e) {
            throw e;
        }
        byte[] encryptBytes = encrytpData(textBytes);
        String encryptString = Base64.encodeToString(encryptBytes, Base64.DEFAULT);
        if (TextUtils.isEmpty(encryptString)) {
            throw new InvalidObjectException("Can not base64 value");
        }
        return encryptString;
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public String decrypt(String encryptedText) throws Exception {
        if (TextUtils.isEmpty(encryptedText)) {
            throw new InvalidObjectException("Can not decrypt empty value");
        }
        byte[] textBytes = Base64.decode(encryptedText, Base64.DEFAULT);
        byte[] decryptBytes = decryptData(textBytes);
        try {
            return new String(decryptBytes, 0, decryptBytes.length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Timber.d(e, "Unsupported Encoding Exception");
            throw e;
        }
    }
}
