plugins {
	eclipse
	idea
	application
}

application {
	mainClass = "LetterSubstitutionCipher"
}

dependencies {
	implementation(files("libs/acm-2.0.jar"))
}