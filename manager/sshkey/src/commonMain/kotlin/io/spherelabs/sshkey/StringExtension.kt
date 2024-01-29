package io.spherelabs.sshkey

fun String.toPem(): String {
    return this.replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replace("\n", "")
}
