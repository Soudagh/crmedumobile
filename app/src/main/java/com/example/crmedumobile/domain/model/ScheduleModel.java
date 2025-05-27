package com.example.crmedumobile.domain.model;

import androidx.annotation.NonNull;

public class ScheduleModel {
    private String time;
    private String science;
    private String group;
    private String theme;
    private String teacher;
    private String link;

    public ScheduleModel(String time, String science, String group, String theme, String teacher, String link) {
        this.time = time;
        this.science = science;
        this.group = group;
        this.theme = theme;
        this.teacher = teacher;
        this.link = link;
    }

    public ScheduleModel() {}

    public String getTime() {
        return time;
    }

    public String getScience() {
        return science;
    }

    public String getGroup() {
        return group;
    }

    public String getTheme() {
        return theme;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getLink() {
        return link;
    }

    @NonNull
    @Override
    public String toString() {
        return "ScheduleModel{" +
                "time='" + time + '\'' +
                ", science='" + science + '\'' +
                ", group='" + group + '\'' +
                ", theme='" + theme + '\'' +
                ", teacher='" + teacher + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}