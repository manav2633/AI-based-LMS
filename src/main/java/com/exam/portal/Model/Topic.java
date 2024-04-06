package com.exam.portal.Model;

import jakarta.persistence.*;




@Entity
@Table(name = "topic_registration")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for topic_registration table
    private int sequenceNo;

    // @ManyToOne
    private int topicCreatorId;

    // @OneToMany
    private int topicId;

//    @NotBlank(message = " Topic Name cannot be empty")
//    @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Topic Name has invalid characters")
    private String topicName;

//    @NotBlank(message = " Descrpition cannot be empty")
//    @Pattern(regexp = "^[A-Za-z][A-Za-z-'. ][A-Za-z]*$", message = " Descrpition has invalid characters")
    private String topicDescrpition;

    private String videoFile;

    private String transcriptFile;

    private String pdfFile;

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getTopicCreatorId() {
		return topicCreatorId;
	}

	public void setTopicCreatorId(int topicCreatorId) {
		this.topicCreatorId = topicCreatorId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescrpition() {
		return topicDescrpition;
	}

	public void setTopicDescrpition(String topicDescrpition) {
		this.topicDescrpition = topicDescrpition;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public String getTranscriptFile() {
		return transcriptFile;
	}

	public void setTranscriptFile(String transcriptFile) {
		this.transcriptFile = transcriptFile;
	}

	public String getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}

	public Topic(int sequenceNo, int topicCreatorId, int topicId, String topicName, String topicDescrpition,
			String videoFile, String transcriptFile, String pdfFile) {
		super();
		this.sequenceNo = sequenceNo;
		this.topicCreatorId = topicCreatorId;
		this.topicId = topicId;
		this.topicName = topicName;
		this.topicDescrpition = topicDescrpition;
		this.videoFile = videoFile;
		this.transcriptFile = transcriptFile;
		this.pdfFile = pdfFile;
	}

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 @Transient
	    public String getFile() {
	        if (pdfFile== null || sequenceNo == 0 ) return null;
	         
	        return "/user-files/" + pdfFile;
	    }

		@Transient
	    public String getFiles() {
	        if (transcriptFile == null || sequenceNo == 0 ) return null;
	         
	        return "/user-files/" + transcriptFile;
	    }

		@Transient
	    public String getVideo() {
	        if ( videoFile == null || sequenceNo == 0 ) return null;
	         
	        return "/user-files/" + videoFile;
	    }
    

	
    
    
    
    
    
    

	
    
    
}
