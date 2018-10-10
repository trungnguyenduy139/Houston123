package com.trungnguyen.android.houston123;

import android.content.Context;

import com.trungnguyen.android.houston123.ui.userdetail.detailmodel.StudentModel;
import com.trungnguyen.android.houston123.util.PrefsUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnd4 on 10/10/2018.
 */

@RunWith(RobolectricTestRunner.class)
public class HoustonAppUtilTest {

    private PrefsUtil mPrefsUtil;

    private Context mContext;

    @Before
    public void setup() {
        mContext = RuntimeEnvironment.application;
        mPrefsUtil = new PrefsUtil(mContext);
    }


    @Test
    public void testPutListPrefsUtil() {

        final String TEST_LIST_PREF = "test-list_pref";

        List<StudentModel> studentModelsTest = new ArrayList<>();

        studentModelsTest.add(new StudentModel("Nguyen Duy Trung", "0939102601"));
        studentModelsTest.add(new StudentModel("Lam Viet Tri", "012345679"));
        studentModelsTest.add(new StudentModel("Bui Huu Phuoc", "0987654321"));

        mPrefsUtil.putListPreferences(TEST_LIST_PREF, studentModelsTest);

        List<StudentModel> expectedResult;
        try {
            expectedResult = mPrefsUtil.getListPreferences(TEST_LIST_PREF, StudentModel.class);
        } catch (Exception e) {
            expectedResult = new ArrayList<>();
        }
        Assert.assertEquals(studentModelsTest.size(), expectedResult.size());
        Assert.assertEquals(studentModelsTest.get(0).getName(), expectedResult.get(0).getName());
        Assert.assertEquals(studentModelsTest.get(1).getName(), expectedResult.get(1).getName());
        Assert.assertEquals(studentModelsTest.get(2).getName(), expectedResult.get(2).getName());

    }

}
