package dsm.pick2024.domain.attendance.port.out

interface AttendancePort :
    SaveAll,
    FindAttendanceByUserIdPort,
    QueryClassAttendancePort,
    QueryClubAttendancePort,
    FindAllAttendancePort
