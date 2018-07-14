/*
 * Copyright (C) 2018 Federico Iosue (federico.iosue@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.trungnguyen.android.houston123.util;

import android.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import timber.log.Timber;


public final class Security {

    public static final String TAG = Security.class.getSimpleName();

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Timber.d(e, "Something is gone wrong calculating MD5");
        }
        return "";
    }


    public static String encrypt(String value, String password) {
        String encrypedValue = "";
        try {
            DESKeySpec keySpec = new DESKeySpec(password.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            byte[] clearText = value.getBytes("UTF8");
            // Cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            return encrypedValue;
        } catch (InvalidKeyException | NoSuchPaddingException | InvalidKeySpecException | BadPaddingException |
                IllegalBlockSizeException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Timber.d(e, "Something is gone wrong encrypting");
        }
        return encrypedValue;
    }


    public static String decrypt(String value, String password) {
        String decryptedValue;
        try {
            DESKeySpec keySpec = new DESKeySpec(password.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

            decryptedValue = new String(decrypedValueBytes);
        } catch (InvalidKeyException | UnsupportedEncodingException | InvalidKeySpecException |
                NoSuchAlgorithmException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException
                e) {
            Timber.e(TAG, "Error decrypting");
            return value;
            // try-catch ensure compatibility with old masked (without encryption) values
        } catch (IllegalArgumentException e) {
            Timber.e("Error decrypting: old notes were not encrypted but just masked to users");
            return value;
        }
        return decryptedValue;
    }


}
