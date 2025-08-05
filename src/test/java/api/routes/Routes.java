package api.routes;


/* All urls 
Create user POST_url : https://petstore.swagger.io/v2/user
Get user GET_url: https://petstore.swagger.io/v2/user/{username}
Update user PUT_url: https://petstore.swagger.io/v2/user/{username}
Delete user Delete_url: https://petstore.swagger.io/v2/user/{username}
*/

public class Routes {

	
	//User module
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+ "/user/{username}";
	public static String update_url = base_url+ "/user/{username}";
	public static String delete_url = base_url+ "/user/{username}";


	
	
	
}
