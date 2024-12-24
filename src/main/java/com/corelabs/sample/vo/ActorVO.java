
package com.corelabs.sample.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("actorVO")
public class ActorVO{
	private String actorId;
	private String firstName;
	private String lastName;
	private String lastUpdate;
}
