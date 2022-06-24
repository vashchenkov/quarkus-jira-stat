package ru.example.community.jirastat.issuestatus.imp.processorschain;

import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public abstract class IssueChangeChainLink<T> {

    protected abstract T getChangeItem(JiraChangeDTO change);

    protected abstract void processChangeItem(T changeItem);

    protected DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true) // min 2 max 3
            .toFormatter();

}
