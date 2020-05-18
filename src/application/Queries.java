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


	public String PlaceSearchQuery (String title) {

		String s = " select isbn , title , publisher_name ,  price  , quantity   \r\n" +
				" from order_processing_system.book_orders , order_processing_system.book \r\n" +
				" where place = 0 and book_orders.isbn = book.isbn " ;

		if (title != null)
			s += "book_orders.title = '" + title + "' " ;

			s+= ";" ;

		return s ;
	}

	public String PlaceUpdateQuery (String isbn) {

		String s = "update order_processing_system.book_orders\r\n" +
				"set place = 1 \r\n" +
				"where  book_orders.isbn = " + isbn + " ; " ;

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

	public String ConfirmUpdateQuert (String isbn) {

		String s = "delete from order_processing_system.book_orders \r\n" +
				"where isbn = "+ isbn +" ; " ;

		return s ;
	}
	public String searchBookQuery(String cat, String value)
	{
		String s= "select isbn ,  title ,publishing_year, publisher_name , price , quantity , catagory.catagory_name\r\n" +
                "from book  , catagory \r\n"+
                "where book.book_catagory = catagory.id and book."+cat+"= '"+value+"'";
		return s;
	}

}
