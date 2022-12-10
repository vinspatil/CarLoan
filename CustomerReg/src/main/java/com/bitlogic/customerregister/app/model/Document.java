package com.bitlogic.customerregister.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
	@TableGenerator(name = "id_generator", table = "id_gen_Doc", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1700)
        @Id
      @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")	
    private Integer documentId;
	private Integer customerId;
	@Lob
	private byte[] identityProof;
	@Lob
	private byte[] addressProof;
	@Lob
	private byte[] incomeProof1;
	@Lob
	private byte[] incomeProof2;
	@Lob
	private byte[] incomeProof3;
}
