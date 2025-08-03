import java.util.*;

class Movie {
    private String title;
    private List<Integer> ratings;

    public Movie(String title) {
        this.title = title;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
        } else {
            System.out.println("Rating must be between 1 and 10.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        int sum = 0;
        for (int r : ratings) {
            sum += r;
        }
        return (double) sum / ratings.size();
    }

    public void displayInfo() {
        System.out.printf("Movie: %s | Average Rating: %.2f (%d rating(s))%n", 
                          title, getAverageRating(), ratings.size());
    }
}

public class IMDBRatingSystem {
    private static Map<String, Movie> movieMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== IMDb Movie Rating System ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Rate Movie");
            System.out.println("3. Show All Movies and Ratings");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addMovie();
                case 2 -> rateMovie();
                case 3 -> showAllMovies();
                case 0 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    private static void addMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();

        if (movieMap.containsKey(title)) {
            System.out.println("Movie already exists.");
        } else {
            movieMap.put(title, new Movie(title));
            System.out.println("Movie added.");
        }
    }

    private static void rateMovie() {
        System.out.print("Enter movie title to rate: ");
        String title = scanner.nextLine();

        Movie movie = movieMap.get(title);
        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Enter rating (1 to 10): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // consume newline
        movie.addRating(rating);
    }

    private static void showAllMovies() {
        if (movieMap.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            for (Movie m : movieMap.values()) {
                m.displayInfo();
            }
        }
    }
}
