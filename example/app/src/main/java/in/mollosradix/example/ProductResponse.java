package in.mollosradix.example;

import java.util.List;

public class ProductResponse {

    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public class Product {
        private int id;
        private String title;
        private String description;
        private String category;
        private double price;
        private double discountPercentage;
        private double rating;
        private int stock;
        private List<String> tags;
        private String brand;
        private String sku;
        private int weight;
        private Dimensions dimensions;
        private String warrantyInformation;
        private String shippingInformation;
        private String availabilityStatus;
        private List<reviews> reviews;
        private String returnPolicy;
        private int minimumOrderQuantity;
        private Meta meta;
        private List<String> images;
        private String thumbnail;


        public Product(int id, String title, String description, String category, double price, double discountPercentage, double rating, int stock, List<String> tags, String brand, String sku, int weight, Dimensions dimensions, String warrantyInformation, String shippingInformation, String availabilityStatus, List<ProductResponse.reviews> reviews, String returnPolicy, int minimumOrderQuantity, Meta meta, List<String> images, String thumbnail) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.category = category;
            this.price = price;
            this.discountPercentage = discountPercentage;
            this.rating = rating;
            this.stock = stock;
            this.tags = tags;
            this.brand = brand;
            this.sku = sku;
            this.weight = weight;
            this.dimensions = dimensions;
            this.warrantyInformation = warrantyInformation;
            this.shippingInformation = shippingInformation;
            this.availabilityStatus = availabilityStatus;
            this.reviews = reviews;
            this.returnPolicy = returnPolicy;
            this.minimumOrderQuantity = minimumOrderQuantity;
            this.meta = meta;
            this.images = images;
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Dimensions getDimensions() {
            return dimensions;
        }

        public void setDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
        }

        public String getWarrantyInformation() {
            return warrantyInformation;
        }

        public void setWarrantyInformation(String warrantyInformation) {
            this.warrantyInformation = warrantyInformation;
        }

        public String getShippingInformation() {
            return shippingInformation;
        }

        public void setShippingInformation(String shippingInformation) {
            this.shippingInformation = shippingInformation;
        }

        public String getAvailabilityStatus() {
            return availabilityStatus;
        }

        public void setAvailabilityStatus(String availabilityStatus) {
            this.availabilityStatus = availabilityStatus;
        }

        public List<ProductResponse.reviews> getReviews() {
            return reviews;
        }

        public void setReviews(List<ProductResponse.reviews> reviews) {
            this.reviews = reviews;
        }

        public String getReturnPolicy() {
            return returnPolicy;
        }

        public void setReturnPolicy(String returnPolicy) {
            this.returnPolicy = returnPolicy;
        }

        public int getMinimumOrderQuantity() {
            return minimumOrderQuantity;
        }

        public void setMinimumOrderQuantity(int minimumOrderQuantity) {
            this.minimumOrderQuantity = minimumOrderQuantity;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    public class Dimensions {
        private double length;
        private double width;
        private double height;

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }

    public class reviews{
        private int rating;
        private String comment;
        private String date;
        private String reviewerName;
        private String reviewerEmail;

        public reviews() {
        }

        public reviews(int rating, String comment, String date, String reviewerName, String reviewerEmail) {
            this.rating = rating;
            this.comment = comment;
            this.date = date;
            this.reviewerName = reviewerName;
            this.reviewerEmail = reviewerEmail;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getReviewerName() {
            return reviewerName;
        }

        public void setReviewerName(String reviewerName) {
            this.reviewerName = reviewerName;
        }

        public String getReviewerEmail() {
            return reviewerEmail;
        }

        public void setReviewerEmail(String reviewerEmail) {
            this.reviewerEmail = reviewerEmail;
        }
    }

    public class Meta {
        private String createdAt;
        private String updatedAt;
        private String barcode;
        private String qrCode;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
    }
}
