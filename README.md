Implementierungsidee:
Intervalle werden on the fly gemerged. Im besten Fall kann alles in ein
intervall gemerged werden und die Komplexität ist ~O(n). Im schlimmsten 
Fall gibt es nichts zu mergen und die Komplexität ist ~O(n²)
Im Nachhinein betrachtet wäre eine Arraylist auch eine gute Alternative
gewesen, um die indexbasierten Zugriffe auf die Liste effizient zu gestalten.

Sicherstellen der Robustheit:
Wenn ich solchen Code in Produktion bringen würde würde ich zusätzlich
zu den standardmäßigen unit tests den Speicherverbrauch aktiver monitoren,
wenn ich größere inputs erwarte oder ggf. eine Limit einbauen um mich vor
unerwarteten, oder mißbräuchlichen Inputs zu schützen, wenn ich
keine größeren inputs erwarte.

Ein Unittest mit zufällig generierten großen inputs könnte auch Sinn machen,
um die Stabilität des Algorithmus zu testen.

Wenn ich sehr große inputs erwarte würde es sich ggf. lohnen die linked
list durch eine geeignetere Datenstruktur wie etwa einen binary tree zu
ersetzen. Das kommt ganz darauf an wie wichtig einem die Effizienz des
Algorithmus ist. Wenn die Funktion nur sporadisch gebraucht wird ist es
sicher anders zu bewerten als wenn sie jede Stunde millionenfach benutzt 
wird.

Speicherverbrauch:
Wie die Laufzeit hängt der Speicherverbrauch auch davon ab, ob die
Intervalle gemerged werden können oder nicht. Worst case ist wenn keine
Intervalle gemerged werden können und der input 1:1 zurück geliefert wird.
