package Repository

import Entidades.Purchase

class PurchaseRepositoryProvider {
    companion object{
        val purchasesList = mutableListOf<Purchase>(
            Purchase(1L, "1504L", 1L, 350.50, "2023-01-01","20:30"),
            Purchase(2L, "1504L", 4L, 100.75, "2023-01-01","20:51"),
            Purchase(3L, "1504L", 7L, 250.50, "2023-01-01","01:12"),
            Purchase(4L, "1504L", 10L, 50.00, "2023-01-01","09:52"),
            Purchase(5L, "1504L", 13L, 1350.15, "2023-01-01","10:32"),
            Purchase(6L, "2802L", 2L, 20.30, "2023-01-01","16:29"),
            Purchase(7L, "2802L", 9L, 450.75, "2023-01-01","15:56"),
            Purchase(8L, "2802L", 11L, 500.00, "2023-01-01","18:55"),
            Purchase(9L, "1510L", 3L, 350.50, "2023-01-01","11:20"),
            Purchase(10L, "1510L", 5L, 150.00, "2023-01-01","22:30")
        )
    }
}