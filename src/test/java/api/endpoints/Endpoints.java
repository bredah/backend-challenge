package api.endpoints;

public enum Endpoints {
	ALBUMS("/albums"),
	COMMENTS("/comments"),
	PHOTOS("/photos"),
	POSTS("/posts"),
	TODOS("/todos"),
	USERS("/users");
		
	private String path;
	
	Endpoints(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}