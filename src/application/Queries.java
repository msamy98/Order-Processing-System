package application;

public class Queries {


	public String  promotionSearchQuery (String userName) {

		String s = "select * " +
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
		String s ="select book.isbn ,  title ,publishing_year, publisher_name , price , quantity ,GROUP_CONCAT(authortable.authorname ) as author, catagory.catagory_name\r\n" +
				"                from book  , catagory ,authortable\r\n" +
				"                where book.book_catagory = catagory.id and book."+cat+"= '"+value+"'and book.isbn=authortable.isbn \r\n" +
				"                group by book.isbn";
		return s;
	}
    public String searchBookQueryAuthor(String value)
    {
    	String s ="select book.isbn ,  title ,publishing_year, publisher_name , price , quantity ,GROUP_CONCAT(authortable.authorname ) as author, catagory.catagory_name\r\n" +
				"                from book  , catagory ,authortable\r\n" +
				"                where book.book_catagory = catagory.id and authortable.authorname= '"+value+"'and book.isbn=authortable.isbn \r\n" +
				"                group by book.isbn";
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
		 String query="UPDATE order_processing_system.book SET quantity =quantity -"+ num+" WHERE (ISBN = '" + isbn + "');";
		return query;
	 }





		public String TopSelling() {
			// TODO Auto-generated method stub
			String s = "select book.title , sum(reporttable.quantity) as number_of_sold\r\n" +
					"from  reporttable , book\r\n" +
					"where ( buyingDate  > DATE_ADD(Now(), INTERVAL - 90 DAY)) and ( book.ISBN = reporttable.bookid)\r\n" +
					"group by  reporttable.bookId\r\n" +
					"order by number_of_sold DESC\r\n" +
					"limit 10; ";
			return s ;
		}

		public String  Topcustomer() {

			String s = "select reporttable.userName , sum(quantity) as number_of_books \r\n" +
					"from  order_processing_system.reporttable\r\n" +
					"where ( buyingDate  > DATE_ADD(Now(), INTERVAL - 90 DAY))\r\n" +
					"group by username \r\n" +
					"order by number_of_books DESC\r\n" +
					"limit 5; \r\n" ;

			return s ;
		}

		public String totalSalesBooks() {

			String s = "select isbn , title , sum(reporttable.quantity * book.price) as total_sales \r\n" +
					"from order_processing_system.book , order_processing_system.reporttable\r\n" +
					"where   (book.isbn = reporttable.bookid)  and ( buyingDate  > DATE_ADD(Now(), INTERVAL - 30 DAY))\r\n" +
					"group by reporttable.bookId ;" ;


			return s;
		}
		public String checkOutCart(int isbn,String date,String user_name ,int q  )
	     {
	         String query="INSERT INTO order_processing_system.reporttable VALUES("+
	             "'"+isbn+"',"+
	                 "'"+date+"',"+
	                 "'"+user_name+"',"+
	                 "'"+q+"')";
	        return query;
	     }

	 public String modifyPersonalInfoUser(String user_name) {
		 String query = "SELECT * FROM order_processing_system.users WHERE user_name = \'" + user_name + "\';";
		 return query;
	 }

	 public String modifyPersonalInfoUpdateUser(String user_name, String param, String value) {
		 String query = "UPDATE order_processing_system.users SET " + param + "=\'" + value + "\' WHERE (user_name = \'" + user_name + "\');";
		 return query;
	 }


}
