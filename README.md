# project-and-training-RSA

## Aufgabe 5

### e)
RSA is not widely used in the industry anymore. Write a brief paragraph (< 300 words)
describing where RSA is still used, why it is not used as widely as before, what are some of
the popular encryption methods today and what their advantages over RSA are.

Der grösste Nachteil von RSA ist, dass das Verschlüsseln von grossen Datenmengen (z.B. über Streams) langsam ist. Und da in der heutigen Zeit die Datenströme immer grösser werden und trotzdem verschlüsselt und schnell sein sollten (z.B. HTTPS Kommunikation), wird RSA als asymmetrisches Kryptosystem zusammen mit symmetrischen Kryptosystemen (ein Schlüssel für beide Parteien; z.B. AES) eingesetzt. Dabei wir das RSA verfahren verwendet, um den gemeinsamen Schlüssel auszutauschen und wenn das Geschehen ist, erfolgt die ganze Kommunikation nur noch über den einen gemeinsamen Schlüssel.

Der Vorteil von symmetrischen Kryptosystemen ist eine viel schnellere Ver- und Entschlüsselung, als über RSA und es besitzt nicht den Schwachpunkt mit Faktorisieren bei schlecht gewählten Primzahlen. Auch müssen dafür keine grossen Primzahlen gefunden werden, da es meist auf einem zufälligen Schlüssel basiert. Mit Perfect Forward Secrecy (PFS) bleibt die Kommunikation auch dann noch *geheim*, wenn der Schlüssel irgendwann publik werden sollte.

Andere asymmetrische Kryptosysteme sind:
- Digital Signature Algorithm (DSA)
- Elliptic Curve DSA (ECDSA)
- Ed25519

