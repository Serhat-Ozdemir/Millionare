package milyoner;

public class Question 
{
	//A class that we storage questions it includes Strings and int variables.
	private String category;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correctAnswer;
	private int difficult;
	private boolean useInfo;
	private String trueFalse;
	

	public Question(String category, String question, String option1, String option2, String option3, String option4,
			String correctAnswer, int difficult, String trueFalse) {
		super();
		this.category = category;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
		this.difficult = difficult;
		useInfo = false;
		this.trueFalse = trueFalse;
	}
	
	public String getCategory() 
	{
		return category;
	}

	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getQuestion() 
	{
		return question;
	}

	public void setQuestion(String question) 
	{
		this.question = question;
	}

	public String getOption1() 
	{
		return option1;
	}

	public void setOption1(String option1) 
	{
		this.option1 = option1;
	}

	public String getOption2() 
	{
		return option2;
	}

	public void setOption2(String option2) 
	{
		this.option2 = option2;
	}

	public String getOption3() 
	{
		return option3;
	}

	public void setOption3(String option3) 
	{
		this.option3 = option3;
	}

	public String getOption4() 
	{
		return option4;
	}

	public void setOption4(String option4) 
	{
		this.option4 = option4;
	}

	public String getCorrectAnswer() 
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) 
	{
		this.correctAnswer = correctAnswer;
	}

	public int getDifficult() 
	{
		return difficult;
	}

	public void setDifficult(int difficult) 
	{
		this.difficult = difficult;
	}
	public boolean getUseInfo() 
	{
		return useInfo;
	}
	public void setUseInfo(boolean useInfo)
	{
		this.useInfo = useInfo;
	}

	public String getIsTrueFalse() 
	{
		return trueFalse;
	}

	public void setIsTrueFalse(String trueFalse) 
	{
		this.trueFalse = trueFalse;
	}
}