package io.nitinbhatt.moviecatalogservice.resources;

import io.nitinbhatt.moviecatalogservice.models.CatalogItem;
import io.nitinbhatt.moviecatalogservice.models.Movie;
import io.nitinbhatt.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")             // when url /catalog is called , load this resource class
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    // when url is /catalog/userId , call this method and pass the userId as variable to method
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //Call 1 - Call Ratings-data-service Microservice to get all rated Movie IDs for a userID provided
        // Get the list back as instances of UserRating class, note: we should not return list as reply but object.
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            // Call 2- For each movieID (received as result of above Microservice call 1)
            // ,call Movies-info-service and get details for each Movie

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            // Put the information all together
            return new CatalogItem(movie.getName(), "Catalog Desc", rating.getRating());
        })
        .collect(Collectors.toList());

    }

}