Trombone-Hero
=============

Song File Format
-------------

```Song name
Tempo (integer: beats per minute)
Song Length (in number of beats)
Number of Notes
<Note 1>
<Note 2>
.
.
<Note n> // where n = number of notes```

Each note line should have this format:

```1 2 3 (4)````

1. Note Name (String, note name plus optional flat (b) or sharp (#)) 
2. Start beat [float]
3. Length (# of beats) [int]
4. Optional, octave number (defaults to 1) [int]