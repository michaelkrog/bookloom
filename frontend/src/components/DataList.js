

export default function DataList({ children }) {

    return (
        <ul
            className="divide-y divide-gray-100 overflow-hidden bg-white shadow-sm ring-1 ring-gray-900/5 sm:rounded-xl"
        >
            {children}
        </ul>
    )
}