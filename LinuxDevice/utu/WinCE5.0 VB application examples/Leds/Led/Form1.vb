Imports OpenNETCF
Imports OpenNETCF.IO

Public Class Form1

    Dim pGPFCON As New PhysicalAddressPointer(&H56000050, 1)
    Dim pGPFDAT As New PhysicalAddressPointer(&H56000054, 1)
    Dim pGPFUP As New PhysicalAddressPointer(&H56000058, 1)

    Dim rGPFCON, rGPFDAT, rGPFUP As Byte

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub ButtonLedOn1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOn1.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT And (Not (1 << 4))
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOn2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOn2.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT And (Not (1 << 5))
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOn3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOn3.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT And (Not (1 << 6))
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOn4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOn4.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT And (Not (1 << 7))
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOff1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOff1.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT Or (1 << 4)
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOff2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOff2.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT Or (1 << 5)
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOff3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOff3.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT Or (1 << 6)
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub

    Private Sub ButtonLedOff4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonLedOff4.Click
        rGPFDAT = pGPFDAT.ReadByte
        rGPFDAT = rGPFDAT Or (1 << 7)
        pGPFDAT.WriteByte(rGPFDAT)
    End Sub
End Class
