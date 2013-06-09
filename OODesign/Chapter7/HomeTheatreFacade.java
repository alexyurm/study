//Note: The code is only to show the Facade Pattern and it's not compilable.

class HomeTheaterFacade {
   Amplifier amp;
   Tuner tuner;
   DvdPlayer dvd;
   CdPlayer cd;
   Projector projector;
   TheaterLights lights;
   Screen screen;
   PopcornPopper popper;

   public HomeTheaterFacade(Amplifier amp,
                           Tuner tuner,
                           DvdPlayer dvd,
                           CdPlayer cd,
                           Projector projector,
                           Screen screen,
                           TheaterLights lights
                           PopcornPopper popper) {
      
     this.amp = amp;
     this.tuner = tuner;
     this.dvd = dvd;
     this.cd = cd;
     this.projector = projector;
     this.screen = screen;
     this.lights = lights;
     this.popper = popper;
   }

   //Other methods here
   public void watchMovie(String movie) {
      System.out.println(Get ready to watch a movie...);
      popper.on();
      popper.pop();
      lights.dim(10);
      screen.down();
      projector.on();
      projector.wideScreenMode();
      amp.on();
      amp.setDvd(dvd);
      amp.setSurroundSound();
      amp.setVolumn(5);
      dvd.on();
      dvd.play(movie);
   }

   public void endMovie() {
      System.out.println("Shutting movie theater down...");
      popper.off();
      lights.on();
      screen.up();
      projector.off();
      amp.off();
      dvd.stop();
      dvd.eject();
      dvd.off();
   }
}

class HomeTheaterTestDrive {
   public static void main(String[] args) {
      //instantiate components here

      HomeTheaterFacade homeTheater = new HomeTheater(amp, tuner, dvd, cd,
                                          projector, screen, lights, popper);

      homeTheater.watchMovie("Raiders of the Lost Ark");
      homeTheater.endMovie();
   }
}


