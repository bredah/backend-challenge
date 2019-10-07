package api.model;

public class Photo {

	private int id;
	private int albumId;
	private String title;
	private String url;
	private String thumbnailUrl;

	public static class Builder {
		private int id;
		private int albumId;
		private String title;
		private String url;

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setAlbumId(int albumId) {
			this.albumId = albumId;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Photo build() {
			return new Photo(this);
		}

	}

	public Photo() {
	}

	private Photo(Builder builder) {
		this.id = builder.id;
		this.albumId = builder.albumId;
		this.title = builder.title;
		this.url = builder.url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", albumId=" + albumId + ", title=" + title + ", url=" + url + ", thumbnailUrl="
				+ thumbnailUrl + "]";
	}
}
