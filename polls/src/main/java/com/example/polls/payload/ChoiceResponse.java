package com.example.polls.payload;

public record ChoiceResponse (
    long id,
    String text,
    long voteCount
){}
