package com.example.demo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFilter;

public class Interview {

	@Valid
	private InterviewModel interview;

	@JsonFilter("InterviewModelFilter")
	public static class InterviewModel {

		@NotBlank
		private String title;
		
		@Pattern(regexp = "REQUEST")
		private String type;
		
		private String status;

		public InterviewModel(String title, String status) {
			this.title = title;
			this.status = status;
		}

		public InterviewModel() {}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public InterviewModel getInterview() {
		return interview;
	}

	public void setInterview(InterviewModel interview) {
		this.interview = interview;
	}
}



