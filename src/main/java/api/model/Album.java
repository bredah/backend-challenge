package api.model;

public class Album {
	private int userId;
	private int id;
	private String title;

	public static class Builder {
		private int userId;
		private int id;
		private String title;

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Album build() {
			return new Album(this);
		}
	}

	public Album() {
	}

	private Album(Builder builder) {
		this.userId = builder.userId;
		this.id = builder.id;
		this.title = builder.title;
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

	@Override
	public String toString() {
		return "Album [userId=" + userId + ", id=" + id + ", title=" + title + "]";
	}

}
