Public Class FormMain

    Private Sub FormMain_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        ComboBoxCom.SelectedIndex = 0
        ComboBoxBaudrate.SelectedIndex = 1
        SerialPort1.Close()
        SerialPort1.PortName = ComboBoxCom.Text
        SerialPort1.BaudRate = CInt(ComboBoxBaudrate.Text)
        Try
            SerialPort1.Open()
        Catch ex As Exception
            MsgBox(ComboBoxCom.Text & " can not be opened.")
        End Try

    End Sub

    Private Sub ComboBoxCom_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBoxCom.SelectedIndexChanged
        SerialPort1.Close()
        SerialPort1.PortName = ComboBoxCom.Text
        Try
            SerialPort1.Open()
        Catch ex As Exception
            MsgBox(ComboBoxCom.Text & " can not be opened.")
        End Try
    End Sub

    Private Sub ComboBoxBaudrate_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBoxBaudrate.SelectedIndexChanged
        SerialPort1.Close()
        SerialPort1.BaudRate = CInt(ComboBoxBaudrate.Text)
        Try
            SerialPort1.Open()
        Catch ex As Exception
            'MsgBox(ComboBox1.Text & " can not be opened.")
        End Try

    End Sub

    Private Sub ButtonClearReceived_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonClearReceived.Click
        TextBoxReceived.Text = ""
    End Sub

    Private Sub ButtonSend_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonSend.Click

        If SerialPort1.IsOpen Then
            SerialPort1.Write(TextBoxSending.Text)
            TextBoxSending.Text = ""
        End If

    End Sub

    Private Sub ButtonClearSending_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ButtonClearSending.Click
        TextBoxSending.Text = ""
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick

        Dim str As String = ""
        Dim ret As Byte

        If SerialPort1.IsOpen Then

            ret = SerialPort1.BytesToRead
            If Not ret = 0 Then
                Try
                    str = SerialPort1.ReadExisting
                Catch ex As Exception
                End Try

                str = TextBoxReceived.Text & str
                TextBoxReceived.Text = str
            End If

        End If
    End Sub

End Class
