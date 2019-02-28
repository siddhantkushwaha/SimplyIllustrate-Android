package com.simplyillustrate.simplyillustrate.models;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticeQuestion implements Parcelable {

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("incorrectAnswers")
    @Expose
    private List<String> incorrectAnswers = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("correctAnswer")
    @Expose
    private String correctAnswer;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public final static Parcelable.Creator<PracticeQuestion> CREATOR = new Creator<PracticeQuestion>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PracticeQuestion createFromParcel(Parcel in) {
            return new PracticeQuestion(in);
        }

        public PracticeQuestion[] newArray(int size) {
            return (new PracticeQuestion[size]);
        }

    };

    protected PracticeQuestion(Parcel in) {
        in.readList(this.tags, (java.lang.String.class.getClassLoader()));
        in.readList(this.incorrectAnswers, (java.lang.String.class.getClassLoader()));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.difficulty = ((String) in.readValue((String.class.getClassLoader())));
        this.question = ((String) in.readValue((String.class.getClassLoader())));
        this.correctAnswer = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PracticeQuestion() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("question = " + question)
                .append("correctAnswer = " + correctAnswer)
                .append("createdAt = " + createdAt).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(tags);
        dest.writeList(incorrectAnswers);
        dest.writeValue(id);
        dest.writeValue(user);
        dest.writeValue(type);
        dest.writeValue(difficulty);
        dest.writeValue(question);
        dest.writeValue(correctAnswer);
        dest.writeValue(createdAt);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}
