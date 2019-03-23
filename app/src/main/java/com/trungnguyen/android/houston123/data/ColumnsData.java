package com.trungnguyen.android.houston123.data;

import com.google.gson.annotations.SerializedName;

public class ColumnsData {
    @SerializedName("age")
    public AgeData age;
    @SerializedName("children_age")
    public ChildrenAgeData children_age;
    @SerializedName("children_grade")
    public ChildrenGradeData children_grade;
    @SerializedName("children_subject_scores_chemistry")
    public ChildrenSubjectScoresChemistry children_subject_scores_chemistry;
    @SerializedName("children_subject_scores_english")
    public ChildrenSubjectScoresEnglish children_subject_scores_english;
    @SerializedName("children_subject_scores_literature")
    public ChildrenSubjectScoresLiterature children_subject_scores_literature;
    @SerializedName("children_subject_scores_math")
    public ChildrenSubjectScoresMath children_subject_scores_math;
    @SerializedName("children_subject_scores_physic")
    public ChildrenSubjectScoresPhysic children_subject_scores_physic;
    @SerializedName("education")
    public EducationData education;
    @SerializedName("gender")
    public GenderData gender;
    @SerializedName("has_cellphone")
    public HasCellphone has_cellphone;
    @SerializedName("income")
    public Income income;
    @SerializedName("job")
    public JobData job;
    @SerializedName("marital")
    public MaritalData marital;
    @SerializedName("use_facebook")
    public UseFacebook use_facebook;

}
