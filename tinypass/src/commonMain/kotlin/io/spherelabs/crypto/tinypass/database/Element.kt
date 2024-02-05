package io.spherelabs.crypto.tinypass.database

import com.benasher44.uuid.Uuid
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

sealed interface DatabaseElement {
    val uuid: Uuid
    val times: TimeData?
    val icon: PredefinedIcon
    val customIconUuid: Uuid?
    val tags: List<String>
}

enum class PredefinedIcon {
    Key,
    World,
    Warning,
    NetworkServer,
    MarkedDirectory,
    UserCommunication,
    Parts,
    Notepad,
    WorldSocket,
    Identity,
    PaperReady,
    Digicam,
    IRCommunication,
    MultiKeys,
    Energy,
    Scanner,
    WorldStar,
    CDRom,
    Monitor,
    Email,
    Configuration,
    ClipboardReady,
    PaperNew,
    Screen,
    EnergyCareful,
    EmailBox,
    Disk,
    Drive,
    PaperQ,
    TerminalEncrypted,
    Console,
    Printer,
    ProgramIcons,
    Run,
    Settings,
    WorldComputer,
    Archive,
    HomeBanking,
    DriveWindows,
    Clock,
    EmailSearch,
    PaperFlag,
    Memory,
    TrashBin,
    Note,
    Expired,
    Info,
    Package,
    Folder,
    FolderOpen,
    FolderPackage,
    LockOpen,
    PaperLocked,
    Checked,
    Pen,
    Thumbnail,
    Book,
    List,
    UserKey,
    Tool,
    Home,
    Star,
    Tux,
    Feather,
    Apple,
    Wiki,
    Money,
    Certificate,
    BlackBerry
}
