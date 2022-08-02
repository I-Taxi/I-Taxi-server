package com.itaxi.server.post.application;

import com.itaxi.server.place.domain.Place;
import lombok.*;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.Joiner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.stream.Stream;

public class PostDto{
    @Getter
    public static class GetPostReq {

        @Builder
        public GetPostReq() {

        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddPostReq {
        private String uid;
        private Long depId;
        private Long dstId;
        private LocalDateTime deptTime;
        private int capacity;
        private int status = 0;
        private int luggage;
        private Integer postType;
    }

    @Getter
    public static class AddPostPlaceReq {
        //private String uid;
        private Place departure;
        private Place destination;
        private LocalDateTime deptTime;
        private int capacity;
        private int status;
        private Integer postType;
        private int luggage;

        @Builder
        public AddPostPlaceReq(AddPostReq req, Place departure, Place destination) {
            this.departure = departure;
            this.destination = destination;
            this.postType = req.getPostType();
            this.deptTime = req.getDeptTime();
            this.capacity = req.getCapacity();
            this.luggage = req.getLuggage();
            this.status = 1;
        }

        public Post toEntity() {
            return Post.builder()
                    .departure(this.departure)
                    .destination(this.destination)
                    .deptTime(this.deptTime)
                    .capacity(this.capacity)
                    .status(this.status)
                    .luggage(this.luggage)
                    .postType(this.postType)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private Long id;
        private Place departure;
        private Place destination;
        private LocalDateTime deptTime;
        private int capacity;
        private int participantNum;
        private int status;
        private Integer postType;
        private int largeLuggageNum = 0;
        private int smallLuggageNum = 0;

        @Builder
        public Res(Post post) {
            this.id = post.getId();
            this.departure = post.getDeparture();
            this.destination = post.getDestination();
            this.deptTime = post.getDeptTime();
            this.capacity = post.getCapacity();
            this.participantNum = post.getJoiners().size();
            this.status = post.getStatus();
            this.postType = post.getPostType();
        }
    }

    @Getter
    public static class PostGetRes {
        private Long id;
        private Place departure;
        private Place destination;
        private LocalDateTime deptTime;
        private int capacity;
        private int participantNum;
        private int status;
        private Integer postType;
        private int largeLuggageNum = 0;
        private int smallLuggageNum = 0;

        @Builder
        public PostGetRes(Post post, List<Integer> luggage) {
            this.id = post.getId();
            this.departure = post.getDeparture();
            this.destination = post.getDestination();
            this.deptTime = post.getDeptTime();
            this.capacity = post.getCapacity();
            this.participantNum = post.getJoiners().size();
            this.status = post.getStatus();
            this.postType = post.getPostType();
            //int count = Collections.frequency(post.getJoiners(), 1);
            //post.getJoiners().stream().map(m -> m.getLuggage()==1? this.smallLuggageNum++ : (m.getLuggage() == 2? this.largeLuggageNum++:(null)));
            //List<Integer> luggage = post.getJoiners().stream().map(Joiner::getLuggage).collect(Collectors.toList());
            this.smallLuggageNum = Collections.frequency(luggage, 1);
            this.largeLuggageNum = Collections.frequency(luggage, 2);
        }
    }
}


