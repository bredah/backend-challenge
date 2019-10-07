package api.model;

public class Comment {
	private int id;
	private int postId;
	private String name;
	private String email;
	private String body;

	public static class Builder {
		private int id;
		private int postId;
		private String name;
		private String email;

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setPostId(int postId) {
			this.postId = postId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Comment build() {
			return new Comment(this);
		}
	}

	public Comment() {
	}

	private Comment(Builder builder) {
		this.id = builder.id;
		this.postId = builder.postId;
		this.name = builder.name;
		this.email = builder.email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", postId=" + postId + ", name=" + name + ", email=" + email + ", body=" + body
				+ "]";
	}
}
