package com.moa.moabackend.entity.friend;

import com.moa.moabackend.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//@Builder
@RequiredArgsConstructor
public class FriendResponseDto {

    @Getter
    @Builder
    public static class SearchFriendResDto {
        private String userId;

        private String friendUsername;

        private String imgUrl;

//        private String userId;
    }

        @Getter
        @Builder
        public static class GetMyFriendResDto {
            private Long id;

            private String userId;

            private String friendUsername;

            private String imgUrl;

    }
}
