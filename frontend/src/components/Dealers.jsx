import { useState, useEffect } from 'react'

export default function Dealers({ apiBase }) {
  const [dealers, setDealers] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch(`${apiBase}/dealers`)
      .then(r => r.json())
      .then(setDealers)
      .catch(() => setError('Could not fetch dealers'))
      .finally(() => setLoading(false))
  }, [apiBase])

  if (loading) return <div className="text-slate-500">Loading dealers...</div>
  if (error) return <div className="bg-red-50 text-red-700 p-4 rounded-lg">{error}</div>

  return (
    <div>
      <h2 className="text-2xl font-bold text-slate-800 mb-4">Dealers</h2>
      <div className="bg-white rounded-xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-slate-100">
            <tr>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">ID</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Name</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Region</th>
            </tr>
          </thead>
          <tbody>
            {dealers.length === 0 ? (
              <tr><td colSpan={3} className="px-4 py-8 text-center text-slate-500">No dealers. Run ETL to load data.</td></tr>
            ) : (
              dealers.map((d) => (
                <tr key={d.id} className="border-t border-slate-100 hover:bg-slate-50">
                  <td className="px-4 py-3">{d.id}</td>
                  <td className="px-4 py-3 font-medium">{d.name}</td>
                  <td className="px-4 py-3">{d.region}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  )
}
