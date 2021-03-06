package api.model;

public class Post {

	private int userId;
	private int id;
	private String title;
	private String body;

	public static class Builder {
		private int userId;
		private int id;
		private String title;
		private String body;

		public Builder setUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setBody(String body) {
			this.body = body;
			return this;
		}

		public Post build() {
			return new Post(this);
		}

	}

	public Post() {

	}

	private Post(Builder builder) {
		this.userId = builder.userId;
		this.id = builder.id;
		this.title = builder.title;
		this.body = builder.body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}

}
