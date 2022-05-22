package com.hcl.beans;

import lombok.*;

@Data
@NoArgsConstructor
public class ResponseMessage {

	private String message;
	private int errorCode;

}
