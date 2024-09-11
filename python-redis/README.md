# Instrukcijos

Šis pavyzdys naudoją Python. Atsisiųskite ir susidiekite [iš čia](https://www.python.org/downloads/release/python-3119/). Windows aplinkoje rinkitės "Windows installer (64-bit)".

Tam, kad projektas veiktų reikia vertualios Python aplinkos, kurioje bus sudiegiamos reikalingos bibliotekos (Flask web karkasas ir Redis klientas).

```
python3 -m venv ./.venv
source ./.venv/bin/activate
```

Sudiegiame reikalingas bibliotekas

```
pip3 install -r requirements
```

Pasileidžiame serverį (jei Windows):

```
run.bat
```

Pasileidžiame serverį (jei Linux / Mac):

```
./run.sh
```

Įkeliant užduotį į e-mokymus, į archyvą `.venv` katalogo neįtraukite!
