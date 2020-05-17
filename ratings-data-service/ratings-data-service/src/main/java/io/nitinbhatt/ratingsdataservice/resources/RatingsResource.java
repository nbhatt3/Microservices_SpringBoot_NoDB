package io.nitinbhatt.ratingsdataservice.resources;

import io.nitinbhatt.ratingsdataservice.models.Rating;
import io.nitinbhatt.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    // when call is made to endpoint /ratingsdata/movieId
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings= Arrays.asList(
                new Rating("M1234", 4),
                new Rating("M5678", 3)
        );
        UserRating userRating= new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
