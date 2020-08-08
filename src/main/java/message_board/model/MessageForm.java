package message_board.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageForm {
	@NotBlank(message = "タイトルが未記入です。")
	@Size(max = 20, message = "タイトルは20文字以内にしてください。")
	private String title;

	@NotBlank(message = "名前が未記入です。")
	@Size(max = 20, message = "名前は20文字以内にしてください。")
	private String name;
	
	@NotBlank(message = "本文が未記入です。")
	@Size(max = 255, message = "本文は255文字以内にしてください。")
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
