package application;

public class Queries {


	public String  promotionSearchQuery (String userName) {

		String s = "select user_name , first_name , last_name , e_mail , shipping_address " +
				"from order_processing_system.users " +
				"where user_type != 0  " ;

		if (userName != null)
			s += "and user_name = \'" + userName + "\' " ;

		s+= ";" ;

		return  s  ;
	}


	public String promtionUpdateQuery(String userName ) {

		String s = "update order_processing_system.users " +
				"set user_type = 0 " +
				"where user_name = \'"+ userName + "\' ;" ;

		return s;
	}



	public String PlaceUpdateQuery (String isbn) {

		String s = "UPDATE order_processing_system.book_orders SET place = \'1\' WHERE book_orders.ISBN = \'" + isbn + "\' ; " ;

		return s ;

	}


	public String ConfirmSearchQuery (String title) {

		String s = " select isbn , title , publisher_name ,  price  , quantity   \r\n" +
				" from order_processing_system.book_orders , order_processing_system.book\r\n" +
				" where place = 1 and book_orders.isbn = book.isbn " ;

		if (title != null)
			s += "book_orders.title = '" + title + "' " ;

			s+= ";" ;

		return s ;

	}

	public String confirmDeleteQuery (String isbn) {

		String s = "DELETE FROM order_processing_system.book_orders WHERE ISBN = "+ isbn +" ; " ;

		return s ;
	}

	public String confirmUpdateQuery(String isbn) {
		String s = "UPDATE order_processing_system.book SET quantity = threshold WHERE (ISBN = \'" + isbn + "\');";
		System.out.println(isbn);
		return s;
	}
	public String searchBookQuery(String cat, String value)
	{
		String s= "select isbn ,  title ,publishing_year, publisher_name , price , quantity , catagory.catagory_name\r\n" +
                "from book  , catagory \r\n"+
                "where book.book_catagory = catagory.id and book."+cat+"= '"+value+"'";
		return s;
	}



	public String placeSelectAll() {
		return "SELECT * FROM order_processing_system.book_orders;";
	}

	 public String placeSelectJoinBook(String title) {
		 String query = "SELECT title,quantity,price,publisher_name,book.ISBN,order_date,order_id FROM order_processing_system.book_orders,order_processing_system.book WHERE book.ISBN = book_orders.ISBN AND book_orders.place = 0";
		 if(title != null)
			 query = query + " AND book.title = \'" + title + "\'";
		 query+= ";";
		 return query;
	 }

	 public String confirmSelectJoinBook(String title) {
		 String query = "SELECT title,quantity,price,publisher_name,book.ISBN,order_date,order_id FROM order_processing_system.book_orders,order_processing_system.book WHERE book.ISBN = book_orders.ISBN AND book_orders.place = 1";
		 if(title != null)
			 query = query + " AND book.title = \'" + title + "\'";
		 query+= ";";
		 return query;
	 }
	 public String checkOutCart(int isbn,int num)
	 {
		 String query="UPDATE order_processing_system.book SET quantity =quantity -"+ num+" WHERE (ISBN = '" + isbn + "')";
		return query;
	 }

}
