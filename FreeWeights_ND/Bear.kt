import processing.core.PApplet
//import kotlin.random.*

val original = 500

fun main(args: Array<String>) {
  PApplet.main("Mondrian");
}

class Mondrian : PApplet() {
    override fun settings() = size (500,500)

    override fun setup() {
    	background(255F);
		noLoop()
    }

    override fun draw() {
        option1(0f, 0f, width, height)
    }

    fun option1(x: Float, y: Float, w: Float, h: Float) {
    	if (n == 0) return
	
	else{

		/*allows for a random point to be selected within the middle 1/3 of the region*/
		var randomstartx = ((w/3)+x)
		var randomendx = ((2*w/3)+x)


		/*allows for a random point to be selected within the middle 1/3 of the region*/
		var randomstarty = ((h/3)+y)
		var randomendy = ((2*h/3)+y)
		
		
		var x1 = random(randomstartx .. randomendx)
		var y1 = random(randomstarty .. randomendy)

		/*This is here because I'm having issues with the randomInt range*/
		println("$randomstartx $randomendx")
		println("$randomstarty $randomendy")

		/*The random points as floats so they can be used to draw a line*/
		var x0 = x1.toFloat()
		var y0 = y1.toFloat()

		line(x0, y, x0, y+h)
		line(x, y0, x+w, y0)

		/*Recursively calls the function on the 4 subregions*/
		/*upper left region*/
		option1(x, y, x0, y0)
		
		/*upper right region*/
		option1(x0, y, w-x0, y0)
		
		/*lower left region*/
		option1(x, y0, x0, h-y0)

		/*lower right region*/
		option1(x0, y0, w-x0, h-y0)

		
	}
    }

  }