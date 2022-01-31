package fftl.lookupBoard.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private List<BoardResponse> boardResponses;

    @Override
    public String toString() {
        return "UserResponse{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", boardResponses=" + boardResponses +
            '}';
    }
}
