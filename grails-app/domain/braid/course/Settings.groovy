package braid.course

class Settings {
	
	Integer options = 0
	String bannerColor
	String githubUsername
	
	boolean has(Option option) {
		this.options & option.value
	}
}

enum Option {

	homework(0x1), assignment(0x2),
	codeReview(0x4)
	
	Integer value
	
	Option(Integer value) {
		this.value = value
	}
	
	Integer plus(Option option) {
		this.value + option.value
	}
}
