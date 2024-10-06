package io.spherelabs.sshkey

expect  class MultiplatformKeyPair {
   val privateKey: PrivateKey
   val publicKey: PublicKey
}
