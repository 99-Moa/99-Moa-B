package com.moa.moabackend.entity.friend;

import com.moa.moabackend.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//@Builder
@RequiredArgsConstructor
public class FriendResponseDto {

    @Getter
    public static class SearchFriendResDto {
        private String friendUsername;

        private String imgUrl;

        public SearchFriendResDto(User user) {
            this.friendUsername = user.getUserName();
            this.imgUrl = user.getImgUrl();

        }
    }

        @Getter
        public static class GetMyFriendResDto {
            private Long id;

            private String friendUsername;

            private String imgUrl;

            public GetMyFriendResDto(Long friendId, User user) {
                this.id = friendId;
                this.friendUsername = user.getUserName();
                this.imgUrl = user.getImgUrl();
            }
    }
}
