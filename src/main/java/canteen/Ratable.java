package canteen;

public interface Ratable {
    void rateProduct(int rating);
    double getAvgRating();
    int getNumberOfRatings();
}