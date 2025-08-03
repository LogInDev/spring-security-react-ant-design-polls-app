package com.example.polls.util;

import com.example.polls.model.Poll;
import com.example.polls.model.User;
import com.example.polls.payload.ChoiceResponse;
import com.example.polls.payload.PollResponse;
import com.example.polls.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ModelMapper {
    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote){
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));
        if(userVote != null){
            pollResponse.setSelectedChoice(userVote);
        }

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream()
                .map(choice -> new ChoiceResponse(
                        choice.getId(),
                        choice.getText(),
                        choiceVotesMap.getOrDefault(choice.getId(), 0L)
                )).toList();

        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());

        long totalVotes = pollResponse.getChoices().stream()
                .mapToLong(ChoiceResponse::voteCount)
                .sum();

        pollResponse.setChoices(choiceResponses);
        pollResponse.setCreatedBy(creatorSummary);
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }
}
