package robot;
import lejos.hardware.Audio;
import lejos.hardware.Brick;

/****************************************************************************
 * 
 * Contrôle du haut-parleur et jouer des ons ou une mélodie
 * 
 * Note: "jouer" utilise un tableau de String de notes: do, re, mi...
 * 													dièse: fa#
 * 													bémol: fa/
 * 			Accès aussi un numéro d'octave:			do3, fa#4, do5
 * 												(0 à 7, 3 par défaut)
 * 			Un String vide ou invalide donne un silence.
 *
 */

public class Son {

	// Instruments
	public static final int FLUTE = 0;
	public static final int PIANO = 1;
	public static final int XYLOPHONE = 2;
	
	public static final int DUREE_NOTE = 400;	// ms

	// Sons système
	public static final int BIP = 0;
	public static final int BIPBIP = 1;
	public static final int DESCEND = 2;
	public static final int MONTE = 3;
	public static final int BUZ = 4;

	// Attributs
	private Audio son;
	private int volume;

	public Son(Brick brick) {
		son = brick.getAudio();
		volume(Audio.VOL_MAX / 3);
	}

	public void volume(int volume) {
		son.setVolume(volume);
	}

	public void plus() {
		if (volume < Audio.VOL_MAX)
			volume(++volume);
	}

	public void moins() {
		if (volume > 0)
			volume(--volume);
	}
	
	// jouer un des sons système
	public void bip(int choix) {
		if (choix >= 0 && choix <= 4)
			son.systemSound(choix);
	}
	public void bip() {
		bip(BIP);
	}


	// Jouer un air avec un instrument
	public void jouer(int choix, String[] notes) {
		int[] instrument = quelInstrument(choix);
		if (instrument == null) return;
		for (int i = 0, max = notes.length; i < max; i++)
			son.playNote(instrument, note2freq(notes[0]), DUREE_NOTE);
	}
	public void jouer(int choix, String[] notes, int[] durees) {
		if (notes.length != durees.length) return;
		int[] instrument = quelInstrument(choix);
		if (instrument == null) return;
		for (int i = 0, max = notes.length; i < max; i++)
			son.playNote(instrument, note2freq(notes[i]), durees[i]);
	}
	private int[] quelInstrument(int choix) {
		switch (choix) {
		case FLUTE:
			return Audio.FLUTE;
		case PIANO:
			return Audio.PIANO;
		case XYLOPHONE:
			return Audio.XYLOPHONE;
		}
		return null;
	}
	
	private int note2freq(String note) {
		switch (note.toLowerCase()) {
			case "do0": case "si#0": return 33;
			case "do#0": case "re/0": return 35;
			case "re0": return 37;
			case "re#0": case "mi/0": return 39;
			case "mi0": case "fa/0": return 41;
			case "fa0": case "mi#0": return 44;
			case "fa#0": case "sol/0": return 46;
			case "sol0": return 49;
			case "sol#0": case "la/0": return 52;
			case "la0": return 55;
			case "la#0": case "si/#0": return 58;
			case "si0": case "do/1": return 62;

			case "do1": case "si#1": return 65;
			case "do#1": case "re/1": return 69;
			case "re1": return 73;
			case "re#1": case "mi/1": return 78;
			case "mi1": case "fa/1": return 82;
			case "fa1": case "mi#1": return 87;
			case "fa#1": case "sol/1": return 93;
			case "sol1": return 98;
			case "sol#1": case "la/1": return 104;
			case "la1": return 110;
			case "la#1": case "si/#1": return 117;
			case "si1": case "do/2": return 123;
			
			case "do2": case "si#2": return 131;
			case "do#2": case "re/2": return 139;
			case "re2": return 147;
			case "re#2": case "mi/2": return 156;
			case "mi2": case "fa/2": return 165;
			case "fa2": case "mi#2": return 175;
			case "fa#2": case "sol/2": return 185;
			case "sol2": return 196;
			case "sol#2": case "la/2": return 208;
			case "la2": return 220;
			case "la#2": case "si/#2": return 233;
			case "si2": case "do/3": return 247;
			
			case "do": case "do3": case "si#": case "si#3": return 262;
			case "do#": case "do#3": case "re/": case "re/3": return 277;
			case "re": case "re3": return 294;
			case "re#": case "re#3": case "mi/": case "mi/3": return 311;
			case "mi": case "mi3": case "fa/": case "fa/3": return 330;
			case "fa": case "fa3": case "mi#": case "mi#3": return 349;
			case "fa#": case "fa#3": case "sol/": case "sol/3": return 370;
			case "sol": case "sol3": return 392;
			case "sol#": case "sol#3": case "la/": case "la/3": return 392;
			case "la": case "la3": return 440;
			case "la#": case "la#3": case "si/": case "si/#3": return 466;
			case "si": case "si3": case "do/4": return 494;

			case "do4": case "si#4": return 523;
			case "do#4": case "re/4": return 554;
			case "re4": return 587;
			case "re#4": case "mi/4": return 622;
			case "mi4": case "fa/4": return 659;
			case "fa4": case "mi#4": return 698;
			case "fa#4": case "sol/4": return 740;
			case "sol4": return 784;
			case "sol#4": case "la/4": return 831;
			case "la4": return 880;
			case "la#4": case "si/#4": return 932;
			case "si4": case "do/5": return 988;
			
			case "do5": case "si#5": return 1047;
			case "do#5": case "re/5": return 1109;
			case "re5": return 1175;
			case "re#5": case "mi/5": return 1245;
			case "mi5": case "fa/5": return 1319;
			case "fa5": case "mi#5": return 1397;
			case "fa#5": case "sol/5": return 1480;
			case "sol5": return 1568;
			case "sol#5": case "la/5": return 1661;
			case "la5": return 1760;
			case "la#5": case "si/#5": return 1865;
			case "si5": case "do/6": return 1976;
			
			case "do6": case "si#6": return 2093;
			case "do#6": case "re/6": return 2217;
			case "re6": return 2349;
			case "re#6": case "mi/6": return 2489;
			case "mi6": case "fa/6": return 2637;
			case "fa6": case "mi#6": return 2794;
			case "fa#6": case "sol/6": return 2960;
			case "sol6": return 3136;
			case "sol#6": case "la/6": return 3322;
			case "la6": return 3520;
			case "la#6": case "si/#6": return 3729;
			case "si6": case "do/7": return 3951;
			
			case "do7": case "si#7": return 4186;
			case "do#7": case "re/7": return 4435;
			case "re7": return 4699;
			case "re#7": case "mi/7": return 4978;
			case "mi7": case "fa/7": return 5274;
			case "fa7": case "mi#7": return 5588;
			case "fa#7": case "sol/7": return 5920;
			case "sol7": return 6272;
			case "sol#7": case "la/7": return 6645;
			case "la7": return 7040;
			case "la#7": case "si/#7": return 7459;
			case "si7": case "do/8": return 7902;
		}
		return 0;
		
		/*
			0		1		2		3		4		5			6			7
do ou si♯ 	32,70 	65,41 	130,81 	261,63 	523,25 	1046,50 	2093,00 	4186,01
do♯ ou ré♭ 	34,65 	69,30 	138,59 	277,18 	554,37 	1108,73 	2217,46 	4434,92
ré 			36,71 	73,42 	146,83 	293,66 	587,33 	1174,66 	2349,32 	4698,64
ré♯ ou mi♭ 	38,89 	77,78 	155,56 	311,13 	622,25 	1244,51 	2489,02 	4978,03
mi ou fa♭ 	41,20 	82,41 	164,81 	329,63 	659,26 	1318,51 	2637,02 	5274,04
fa ou mi♯ 	43,65 	87,31 	174,61 	349,23 	698,46 	1396,91 	2793,83 	5587,65
fa♯ ou sol♭ 	46,25 	92,50 	185,00 	369,99 	739,99 	1479,98 	2959,96 	5919,91
sol 		49,00 	98,00 	196,00 	392,00 	783,99 	1567,98 	3135,96 	6271,93
sol♯ ou la♭ 	51,91 	103,83 	207,65 	415,30 	830,61 	1661,22 	3322,44 	6644,88
la 			55,00 	110,00 	220,00 	440,00 	880,00 	1760,00 	3520,00 	7040,00
la♯ ou si♭ 	58,27 	116,54 	233,08 	466,16 	932,33 	1864,66 	3729,31 	7458,62
si ou do♭ 	61,74 	123,47 	246,94 	493,88 	987,77 	1975,53 	3951,07 	7902,13
		 */
	}
}
