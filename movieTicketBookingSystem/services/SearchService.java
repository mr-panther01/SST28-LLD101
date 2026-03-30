package movieTicketBookingSystem.services;
import movieTicketBookingSystem.models.Movie;
import movieTicketBookingSystem.models.Show;
import movieTicketBookingSystem.models.ShowSeat;
import movieTicketBookingSystem.models.Theatre;
import java.time.LocalDate;
import java.util.List;

public interface SearchService {
    List<Movie> getMoviesByCity(String cityId);
    List<Theatre> getTheatresByCity(String cityId);
    List<Show> getShowsForMovie(String movieId, String cityId, LocalDate date);
    List<ShowSeat> getSeatsForShow(String showId);
}