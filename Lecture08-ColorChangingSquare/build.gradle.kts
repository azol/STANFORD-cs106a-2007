plugins {
	eclipse
	idea
	application
}

application {
	mainClass = "ColorChangingSquare"
}

dependencies {
	implementation(files("libs/acm-2.0.jar"))
}