export default function Home() {
    return(
        <>
            <h2>Dies ist eine einfache Anwendung zum Verwalten von Aufgaben (Todos)</h2>
            <p>Unter App werden die Aufgaben in drei Kategorien angezeigt: Offen, In Arbeit und Erledigt.<br/>
                Mit 'Add Aufgabe' lässt sich eine neue Aufgabe erstellen. Mit 'Level up' wird die Aufgabe
                in die nächst höhere Kategorie verschoben. 'Löschen' lässt sich nur betätigen, wenn die
                Aufgabe unter Erledigt steht.<br/>
                Zum Anlegen der Aufgabe muss eine Beschreibung (mind. 5 Zeichen) und der Status (OPEN, IN_PROGRESS, DONE)
                eingegeben werden und mit 'Anlegen' bestätigt werden.<br/>
                Mit 'Bearbeiten' lässt sich die Beschreibung und/oder der Status ändern.
            </p>
        </>
    )
}