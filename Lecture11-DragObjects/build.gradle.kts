plugins {
	eclipse
	idea
	application
}

application {
	mainClass = "DragObjects"
}

dependencies {
	implementation(files("libs/acm-2.0.jar"))
}