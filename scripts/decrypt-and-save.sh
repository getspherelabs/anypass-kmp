#!/bin/bash

decrypt() {
  PASSPHRASE="$1"
  INPUT="$2"
  OUTPUT="$3"

  if [[ -z "$PASSPHRASE" ]]; then
    echo "Passphrase is missed"
    return 1
  fi

  if [[ ! -f "$INPUT" ]]; then
    echo "'$INPUT' not found"
    return 1
  fi

  gpg --quiet --batch --yes --decrypt --passphrase="$PASSPHRASE" --output "$OUTPUT" "$INPUT"
}

decrypt_and_save() {
  local PASSPHRASE="$1"
  local INPUT_FILE="$2"
  local OUTPUT_FILE="$3"

  if [[ -n "$PASSPHRASE" ]]; then
    decrypt "${PASSPHRASE}" "${OUTPUT_FILE}" "${INPUT_FILE}"
  else
    echo "ENCRYPT_KEY is empty"
  fi
}

decrypt_and_save "$PASSPHRASE" androidApp/google-services.json release/google-services.gpg
